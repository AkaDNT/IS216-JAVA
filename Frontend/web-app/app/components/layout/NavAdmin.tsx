"use client";

import Link from "next/link";
import { usePathname } from "next/navigation";
import { cn } from "@/lib/utils";

const navItems = [
  { label: "Dashboard", href: "/dashboard" },
  { label: "Report", href: "/report" },
  { label: "Products", href: "/products" },
  { label: "Customers", href: "/customers" },
  { label: "Employee", href: "/employee" },
];

export default function NavAdmin() {
  const pathname = usePathname();

  return (
    <aside className="w-56 h-screen px-4 py-8 bg-white border-r border-gray-200">
      <h2 className="text-lg font-bold mb-6">DealDeck</h2>
      <nav className="flex flex-col space-y-2">
        {navItems.map((item) => (
          <Link
            key={item.href}
            href={item.href}
            className={cn(
              "px-3 py-2 rounded-lg text-sm font-medium",
              pathname === item.href
                ? "bg-blue-100 text-blue-600"
                : "text-gray-600 hover:bg-gray-100 hover:text-blue-500"
            )}
          >
            {item.label}
          </Link>
        ))}
      </nav>
    </aside>
  );
}
