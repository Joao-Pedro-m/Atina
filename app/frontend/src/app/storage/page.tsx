import Image from "next/image";
import HeaderAtina from "@/components/header-atina";
import ListItems from "@/components/list-items";
import ItemBar from "@/components/item-bar";

export default function Home() {  
  return (
    <div>
      <HeaderAtina />
      <ListItems />
      <ItemBar />
    </div>

  );
}
