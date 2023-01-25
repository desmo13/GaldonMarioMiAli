document.getElementById("provincia").addEventListener("change",mostrarMunicipio)


function mostrarMunicipio(e){
    console.log(e.target.value);
    //document.getElementById("Municipio").querySelectorAll("option").forEach(ele=>{ele.setAttribute("hidden",true)})
    //document.getElementById("Municipio").querySelectorAll(".p"+e.target.value).forEach(ele=>{
     //   ele.removeAttribute("hidden");
   // });
    var formData = new FormData();
    formData.append("municipioId",e.target.value)
    fetch("http://localhost:8080/GaldonMarioMiAli/obtenerMunicipio",{method:"Post",body: formData})
        .then((response) => response.json())
        .then((data) => {
            console.log(data)
            document.getElementById("Municipio").innerHTML=""
            data.forEach(e=>{
                let option = document.createElement("option");
                option.value=e.idMunicipio;
                option.innerText=e.nombre;
                document.getElementById("Municipio").appendChild(option);
            })

        });
}