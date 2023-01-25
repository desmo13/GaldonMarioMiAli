document.getElementById("provincia").addEventListener("change",mostrarMunicipio)


function mostrarMunicipio(e){
    console.log(e.target.value);
    //document.getElementById("Municipio").querySelectorAll("option").forEach(ele=>{ele.setAttribute("hidden",true)})
    //document.getElementById("Municipio").querySelectorAll(".p"+e.target.value).forEach(ele=>{
     //   ele.removeAttribute("hidden");
   // });
    var formData = new FormData();
    formData.append("municipioId",e.target.value)
    fetch("http://localhost:8080/GaldonMarioMiAli/obtenerMunicipio",{method:"Post",body: formData}).then(e=>console.log(e));
}