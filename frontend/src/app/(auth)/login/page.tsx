"use client";
import { AuthForm } from "@/features/auth/AuthForm";

export default function Login() {
  return (
    <AuthForm
      mode="login"
      onSubmitLogin={(data) => {
        console.log("Login", data);
      }}
    />
  );
}
