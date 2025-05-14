import type { Metadata } from "next";
import "./globals.css";
import Navbar from "./components/layout/Navbar";
import { getCurrentUser } from "./actions/getCurrentUser";

export const metadata: Metadata = {
  title: "Bookstore",
  description: "Created by RGBunny",
};

export default async function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  const user = await getCurrentUser();
  return (
    <html lang="en">
      <body>
        <Navbar user={user}></Navbar>
        {children}
      </body>
    </html>
  );
}
