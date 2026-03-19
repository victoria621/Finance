"use client";
import { AuthForm } from "@/features/auth/AuthForm";

export default function Register() {
  return (
    <AuthForm
      mode="register"
      onSubmitRegister={(data) => {
        console.log("Register", data);
      }}
    />
  );
}
