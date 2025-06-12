import type { Metadata } from "next";
import "./globals.css";
import Navbar from "./components/layout/Navbar";
import { getCurrentUser } from "./actions/getCurrentUser";
import { Toaster } from "react-hot-toast";
import AdminHeader from "./components/layout/AdminHeader";
import NavAdmin from "./components/layout/NavAdmin";

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
  if (user?.roles.includes("ROLE_ADMIN"))
    return (
      <html lang="en">
        <body>
          <Toaster></Toaster>
          <div className="flex">
            <NavAdmin />

            <div className="flex-1 flex flex-col">
              <AdminHeader currentUser={user} />

              <main className="p-6 bg-gradient-to-r from-purple-100 via-rose-100 to-white border-b border-gray-200 shadow-md">
                {children}
              </main>
            </div>
          </div>
        </body>
      </html>
    );
  return (
    <html lang="en">
      <body>
        <Toaster></Toaster>
        <Navbar user={user}></Navbar>
        {children}
      </body>
    </html>
  );
}
