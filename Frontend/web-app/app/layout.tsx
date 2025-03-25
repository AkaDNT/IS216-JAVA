import type { Metadata } from "next";
import "./globals.css";


export const metadata: Metadata = {
  title: "Bookstore",
  description: "Created by RGBunny",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <body>{children}</body>
    </html>
  );
}
