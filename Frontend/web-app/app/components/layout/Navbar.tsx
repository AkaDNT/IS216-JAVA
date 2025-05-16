import Image from "next/image";
import Link from "next/link";
import { ShoppingCart, Heart } from "lucide-react";
import { CurrentUser } from "@/app/actions/getCurrentUser";
import AccountDropdown from "./AccountDropdown";
import SearchBar from "../ui/SearchBar";

export default function Navbar({ user }: { user: CurrentUser | null }) {
  return (
    <nav className="w-full bg-white shadow-md px-6 py-4 flex items-center justify-between">
      {/* Left: Logo */}
      <div className="flex-shrink-0">
        <Link href="/">
          <Image
            className="h-10 cursor-pointer"
            src="/logo.png"
            alt="Logo"
            width={120}
            height={50}
          />
        </Link>
      </div>

      {/* Center: Search */}
      <SearchBar></SearchBar>

      {/* Right: Icons */}
      <div className="flex space-x-8 items-center relative">
        <div className="flex flex-col items-center cursor-pointer">
          <ShoppingCart size={24} />
          <span className="text-sm mt-1">Cart</span>
        </div>
        <div className="flex flex-col items-center cursor-pointer">
          <Heart size={24} />
          <span className="text-sm mt-1">Wishlist</span>
        </div>
        <div className="flex flex-col items-center cursor-pointer">
          <AccountDropdown user={user} />
        </div>
      </div>
    </nav>
  );
}
