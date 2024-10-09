import type { JwtPayload } from "jsonwebtoken";

export default interface CustomJwtPayload extends JwtPayload {
  id: number;
  email: string;
  role: string;
}
