import type LoginResponse from "~/types/service/LoginResponse";
import axiosInstance from "./axiosInstance";

class AuthService {
  async login(email: string, password: string): Promise<LoginResponse> {
    try {
      const response = await axiosInstance.post<LoginResponse>(
        "api/v1/auth/signin",
        {
          email,
          password,
        },
      );
      return response.data;
    } catch (error) {
      console.error("Error login:", error);
      throw error;
    }
  }

  async refreshToken(): Promise<LoginResponse> {
    try {
      const response = await axiosInstance.post<LoginResponse>(
        "api/v1/auth/signin/refresh",
        {
          refreshToken: localStorage.getItem("refreshToken"),
        },
      );
      return response.data;
    } catch (error) {
      console.error("Error fetching users:", error);
      throw error;
    }
  }

  async signup(
    firstName: string,
    lastName: string,
    email: string,
    password: string,
  ): Promise<void> {
    try {
      await axiosInstance.post("api/v1/auth/signup", {
        firstName,
        lastName,
        email,
        password,
      });
    } catch (error) {
      console.error("Error signup users:", error);
      throw error;
    }
  }
}

export default new AuthService();
