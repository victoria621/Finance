export default function AuthLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <main className="flex grow items-start justify-center">{children}</main>
  );
}
