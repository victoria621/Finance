"use client";

import { Button } from "@/shared/ui/kit/button";
import { Input } from "@/shared/ui/kit/input";
import * as z from "zod";
import { Controller, useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import {
  Card,
  CardHeader,
  CardTitle,
  CardDescription,
  CardContent,
  CardFooter,
} from "@/shared/ui/kit/card";
import {
  FieldGroup,
  Field,
  FieldLabel,
  FieldError,
} from "@/shared/ui/kit/field";
import Link from "next/link";

const loginSchema = z.object({
  email: z.email("Invalid email address"),
  password: z.string().min(6, "Password must be at least 6 characters"),
});

const registerSchema = z
  .object({
    name: z.string().min(4, "Name must be at least 4 characters"),
    email: z.email("Invalid email address"),
    password: z.string().min(6, "Password must be at least 6 characters"),
    confirmPassword: z.string(),
  })
  .refine((data) => data.password === data.confirmPassword, {
    message: "Passwords do not match",
    path: ["confirmPassword"],
  });

type LoginFormValues = z.infer<typeof loginSchema>;
type RegisterFormValues = z.infer<typeof registerSchema>;

const defaultTitles = {
  login: {
    title: "Sign in",
    subtitle: "Enter your credentials to access your account",
  },
  register: {
    title: "Create account",
    subtitle: "Enter your details to create an account",
  },
} as const;

export type AuthFormMode = "login" | "register";

export type AuthFormProps = {
  mode: AuthFormMode;
  title?: string;
  subtitle?: string;
  onSubmitLogin?: (data: LoginFormValues) => void;
  onSubmitRegister?: (data: RegisterFormValues) => void;
};

export const AuthForm = ({
  mode,
  title,
  subtitle,
  onSubmitLogin,
  onSubmitRegister,
}: AuthFormProps) => {
  const isRegister = mode === "register";
  const schema = isRegister ? registerSchema : loginSchema;
  const titles = defaultTitles[mode];

  const form = useForm<LoginFormValues | RegisterFormValues>({
    resolver: zodResolver(schema),
    defaultValues: isRegister
      ? { name: "", email: "", password: "", confirmPassword: "" }
      : { email: "", password: "" },
  });

  const onSubmit = (data: LoginFormValues | RegisterFormValues) => {
    if (isRegister) {
      onSubmitRegister?.(data as RegisterFormValues);
    } else {
      onSubmitLogin?.(data as LoginFormValues);
    }
  };

  const formId = `auth-form-${mode}`;

  return (
    <Card className="w-full sm:max-w-md mt-20">
      <CardHeader>
        <CardTitle>{title ?? titles.title}</CardTitle>
        <CardDescription>{subtitle ?? titles.subtitle}</CardDescription>
      </CardHeader>
      <CardContent>
        <form id={formId} onSubmit={form.handleSubmit(onSubmit)}>
          <FieldGroup className="gap-6">
            {isRegister && (
              <Controller
                name="name"
                control={form.control}
                render={({ field, fieldState }) => (
                  <Field data-invalid={fieldState.invalid}>
                    <FieldLabel htmlFor={`${formId}-name`}>Name</FieldLabel>
                    <Input
                      {...field}
                      id={`${formId}-name`}
                      aria-invalid={fieldState.invalid}
                      placeholder="John Doe"
                      autoComplete="name"
                    />
                    {fieldState.invalid && (
                      <FieldError errors={[fieldState.error]} />
                    )}
                  </Field>
                )}
              />
            )}
            <Controller
              name="email"
              control={form.control}
              render={({ field, fieldState }) => (
                <Field data-invalid={fieldState.invalid}>
                  <FieldLabel htmlFor={`${formId}-email`}>Email</FieldLabel>
                  <Input
                    {...field}
                    id={`${formId}-email`}
                    aria-invalid={fieldState.invalid}
                    placeholder="example@example.com"
                    autoComplete="email"
                  />
                  {fieldState.invalid && (
                    <FieldError errors={[fieldState.error]} />
                  )}
                </Field>
              )}
            />
            <Controller
              name="password"
              control={form.control}
              render={({ field, fieldState }) => (
                <Field data-invalid={fieldState.invalid}>
                  <FieldLabel htmlFor={`${formId}-password`}>
                    Password
                  </FieldLabel>
                  <Input
                    {...field}
                    id={`${formId}-password`}
                    aria-invalid={fieldState.invalid}
                    placeholder="********"
                    autoComplete={
                      isRegister ? "new-password" : "current-password"
                    }
                    type="password"
                  />
                  {fieldState.invalid && (
                    <FieldError errors={[fieldState.error]} />
                  )}
                </Field>
              )}
            />
            {isRegister && (
              <Controller
                name="confirmPassword"
                control={form.control}
                render={({ field, fieldState }) => (
                  <Field data-invalid={fieldState.invalid}>
                    <FieldLabel htmlFor={`${formId}-confirmPassword`}>
                      Confirm password
                    </FieldLabel>
                    <Input
                      {...field}
                      id={`${formId}-confirmPassword`}
                      aria-invalid={fieldState.invalid}
                      placeholder="********"
                      autoComplete="new-password"
                      type="password"
                    />
                    {fieldState.invalid && (
                      <FieldError errors={[fieldState.error]} />
                    )}
                  </Field>
                )}
              />
            )}
          </FieldGroup>
        </form>
      </CardContent>
      <CardFooter className="flex flex-col gap-6">
        <Button type="submit" form={formId} className="w-full">
          {isRegister ? "Create account" : "Sign in"}
        </Button>
        {isRegister ? (
          <p>
            Already have an account?{" "}
            <Link
              href="/login"
              className="text-primary underline hover:text-blue-400 transition-colors"
            >
              Sign in
            </Link>
          </p>
        ) : (
          <p>
            Don&apos;t have an account?{" "}
            <Link
              href="/register"
              className="underline hover:text-blue-400 transition-colors"
            >
              Register
            </Link>
          </p>
        )}
      </CardFooter>
    </Card>
  );
};
