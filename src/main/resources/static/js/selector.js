document.getElementById("provincia").addEventListener("change",mostrarMunicipio)


function mostrarMunicipio(e){
    console.log(e.target.value);
    //document.getElementById("Municipio").querySelectorAll("option").forEach(ele=>{ele.setAttribute("hidden",true)})
    //document.getElementById("Municipio").querySelectorAll(".p"+e.target.value).forEach(ele=>{
     //   ele.removeAttribute("hidden");
   // });
    fetch("http://localhost:8080/GaldonMarioMiAli/Producto",{method:"Post",body: {municipioId:e.target.value}}).then(e=>console.log(e));
}