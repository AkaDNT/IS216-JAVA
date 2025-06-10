"use client";

import { useState } from "react";

export default function AddToCartSection() {
  const [quantity, setQuantity] = useState(1);

  const handleQuantityChange = (delta: number) => {
    setQuantity((prev) => Math.max(1, prev + delta));
  };

  const handleAddToCart = () => {
    // TODO: Gửi request Add to Cart tại đây
    console.log("Add to cart with quantity:", quantity);
  };

  return (
    <div className="space-y-4">
      {/* Chọn số lượng */}
      <div>
        <label className="text-sm font-medium text-gray-700">Quantity:</label>
        <div className="flex items-center gap-4 mt-2">
          <button
            className="w-8 h-8 rounded-full bg-gray-200 text-lg font-bold"
            onClick={() => handleQuantityChange(-1)}
          >
            -
          </button>
          <span className="text-xl font-semibold">{quantity}</span>
          <button
            className="w-8 h-8 rounded-full bg-gray-200 text-lg font-bold"
            onClick={() => handleQuantityChange(1)}
          >
            +
          </button>
        </div>
      </div>

      {/* Nút Add to Cart */}
      <button
        className="w-full bg-purple-600 text-white py-3 text-lg font-medium rounded-xl shadow hover:bg-purple-700 transition"
        onClick={handleAddToCart}
      >
        Add to Cart
      </button>
    </div>
  );
}
