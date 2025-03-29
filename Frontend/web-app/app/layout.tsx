import type { Metadata } from "next";
import "./globals.css";
import Navbar from "./components/layout/Navbar";

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
      <body>
        <Navbar></Navbar>
        {children}
      </body>
    </html>
  );
}
