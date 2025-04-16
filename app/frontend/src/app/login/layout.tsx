import type { Metadata } from "next";
import { Inter } from "next/font/google";
import "@/app/globals.css";

const inter = Inter({subsets: ["latin"]});

export const metadata: Metadata = {
  title: "Login Page",
  description: "Aba de Login do Atina",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html className="flex justify-center"lang="pt-br">
      <body className={`${inter.className} antialiased h-screen bg-[#A9A9A9] text-xl flex justify-center items-center`}>
        {children}
      </body>
    </html>
  );
}
