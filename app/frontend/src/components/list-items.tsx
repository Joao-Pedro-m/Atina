export default function ListItems(){
    return (
        // REVISAR ESSE COMPONENT PORQUE OS ITEMS SERÃO ADICIONADOS POR UMA REQUISIÇÃO
        <ul className="flex flex-wrap gap-[1%]">
            <div className="flex flex-wrap justify-center m-[1%]">
                <div className="text-center">
                    <img className="w-[150px] h-[150px] object-cover rounded-[10px]" src="https://images.pexels.com/photos/4480453/pexels-photo-4480453.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1" alt="Imagem do Item"/>
                    <p className="whitespace-nowrap m-0 overflow-hidden overflow-ellipsis max-w-[150px]">Item</p>
                </div>
            </div>
        </ul>
    )
}