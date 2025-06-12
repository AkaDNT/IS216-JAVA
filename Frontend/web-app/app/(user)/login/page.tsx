"use client";

import { useActionState } from "react";
import { loginUser } from "./action";

export default function LoginPage() {
  const [state, formAction] = useActionState(loginUser, null);

  return (
    <div className="py-20 px-4 bg-gray-100">
      <div className="bg-white shadow-xl rounded-xl p-8 w-full max-w-md mx-auto">
        <h2 className="text-3xl font-bold text-center text-gray-800 mb-6">
          Login
        </h2>
        <form action={formAction} className="space-y-5">
          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">
              User Name
            </label>
            <input
              name="username"
              type="text"
              placeholder="Your user name"
              required
              className="w-full px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
            />
          </div>
          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">
              Password
            </label>
            <input
              name="password"
              type="password"
              placeholder="••••••••"
              required
              className="w-full px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
            />
          </div>
          {/* Forgot Password link */}
          <p
            onClick={() => (window.location.href = "/forgot-password")}
            className="text-sm text-blue-600 text-right hover:underline cursor-pointer"
          >
            Forgot password?
          </p>
          <button
            type="submit"
            className="w-full bg-blue-600 text-white py-2 rounded-lg hover:bg-blue-700 transition duration-200 cursor-pointer"
          >
            Log in
          </button>
          {state?.message && (
            <p className="text-red-500 text-center text-sm mt-2">
              {state.message}
            </p>
          )}
        </form>

        <p className="text-center text-sm text-gray-600 mt-6">
          Don&apos;t have an account?{" "}
          <span
            onClick={() => (window.location.href = "/register")}
            className="text-blue-600 font-semibold cursor-pointer hover:underline"
          >
            Sign up
          </span>
        </p>
      </div>
    </div>
  );
}
