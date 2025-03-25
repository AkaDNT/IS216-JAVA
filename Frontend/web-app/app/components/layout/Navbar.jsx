import Image from "next/image";
import { ShoppingCart, Heart, User } from "lucide-react";

export default function Navbar() {
  return (
    <nav className="w-full bg-white shadow-md px-6 py-4 flex items-center justify-between">
      {/* Logo bên trái */}
      <div className="flex-shrink-0">
        <Image
          className="h-10 cursor-pointer"
          src="/logo.png"
          alt="Logo"
          width={120}
          height={50}
        />
      </div>

      {/* Thanh tìm kiếm ở giữa */}
      <div className="flex-grow mx-8">
        <input
          type="text"
          placeholder="Tìm kiếm..."
          className="w-full border border-gray-300 rounded-full py-2 px-4 focus:outline-none focus:ring-2 focus:ring-blue-400"
        />
      </div>

      {/* Ba ô icon bên phải */}
      <div className="flex space-x-8">
        <div className="flex flex-col items-center cursor-pointer">
          <ShoppingCart size={24} />
          <span className="text-sm mt-1">Giỏ hàng</span>
        </div>
        <div className="flex flex-col items-center cursor-pointer">
          <Heart size={24} />
          <span className="text-sm mt-1">Yêu thích</span>
        </div>
        <div className="flex flex-col items-center cursor-pointer">
          <User size={24} />
          <span className="text-sm mt-1">Tài khoản</span>
        </div>
      </div>
    </nav>
  );
}
