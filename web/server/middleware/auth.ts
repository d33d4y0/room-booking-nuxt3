import axios from "axios";
import jwt from "jsonwebtoken";
import type CustomJwtPayload from "~/types/service/booking/CustomJwtPayload";
import type LoginResponse from "~/types/service/LoginResponse";

const axiosInstance = axios.create({
  baseURL: "http://localhost:9002/booking",
  timeout: 10000,
  headers: {
    "Content-Type": "application/json",
  },
});
axiosInstance.defaults.withCredentials = true;
const protectedRoutes = ["/booking"];
const unProtectedRoutes = ["/login", "/signup", "/", ""];

export default defineEventHandler(async (event) => {
  const accessToken = getCookie(event, "accessToken");
  const refreshToken = getCookie(event, "refreshToken");
  const requestPath = event.node.req.url ?? "";

  if (unProtectedRoutes.includes(requestPath)) {
    if (accessToken) {
      event.node.res.writeHead(302, {
        Location: "/booking",
      });
      event.node.res.end();
    }
  }

  if (accessToken) {
    const decodedUser = decodeJWT(accessToken);
    event.context.user = decodedUser;
  } else {
    if (refreshToken) {
      await axiosInstance
        .post<LoginResponse>(
          "api/v1/auth/refresh",
          {
            refreshToken,
          },
          {
            headers: {
              Cookie: `refreshToken=${refreshToken}`,
            },
          },
        )
        .then((response) => {
          const { accessToken: newAccessToken } = response.data;
          event.context.user = decodeJWT(newAccessToken);
          // TODO enable HttpOnly
          // event.node.res.setHeader("Set-Cookie", `accessToken=${newAccessToken}; HttpOnly path=/`);
          event.node.res.setHeader(
            "Set-Cookie",
            `accessToken=${newAccessToken}; path=/`,
          );
          return event;
        })
        .catch((error) => {
          console.error("Error refreshing token:", error);
          event.node.res.writeHead(302, {
            Location: "/login",
          });
          event.node.res.end();
        });
    } else {
      if (
        protectedRoutes.some((route) => requestPath?.startsWith(route)) &&
        event.context.user === undefined
      ) {
        event.node.res.writeHead(302, {
          Location: "/login",
        });
        event.node.res.end();
      }
    }
  }
});

function decodeJWT(token: string): CustomJwtPayload {
  try {
    // TODO verify token
    const decodedToken = jwt.decode(token, {
      complete: true,
    }) as unknown as CustomJwtPayload;
    return decodedToken;
  } catch (error) {
    console.error("Invalid token:", error);
    return {} as CustomJwtPayload;
  }
}
