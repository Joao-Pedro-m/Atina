export default function ItemBar (){
    return (
        // VAI RECEBER A FUNÇÃO PARA PESQUISAR ITEMS NA API
        <div className="fixed bg-[#A9A9A9] bottom-0">
            <footer className=" p-[1%] flex w-screen">
                <input className="border-[1px] rounded-full p-2" type="text" id="search_tool"/>
                <input className="self-auto rounded-full bg-[#A9A9A9] ml-2 text-xl px-4 py-1 transition duration-500hover:bg-[#bdbdbd] hover:shadow-[0_0_5px_2px_black]active:bg-[#3a3a3a] active:text-white active:shadow-[inset_0_2px_1px_3px_black]" type="button" id="add_item_button" value="Adicionar Item"/>
            </footer>
        </div>
    )
}