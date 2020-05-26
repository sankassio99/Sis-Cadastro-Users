/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
    function criaAjax(url,dados,funcao)
        {
            let ajax=new XMLHttpRequest();
            ajax.onreadystatechange=funcao;
            ajax.open("GET",url+"?"+dados,true);
            ajax.send();
        }
    function mandarDados() {
        var nome=document.getElementById("nome").value;
        var idade=document.getElementById("idade").value;
        criaAjax("http://localhost:8080/PraticaDaoDataSource/NewInserir","nome="+nome+"&idade="+idade,mostrar);
        limpar();
    }
    
    function listarDados() {
        var nome= "" ;
        criaAjax("http://localhost:8080/PraticaDaoDataSource/ListarTodos","nome="+nome,mostrar);
        limpar();
    }
    
    function deleteDados() {
        var nome=document.getElementById("nome").value;
        criaAjax("http://localhost:8080/PraticaDaoDataSource/DeleteUser","nome="+nome,mostrar);
        limpar();
    }
    
    var selecionado = false;
    var nomeOriginal = null ;
    function pegarPessoa() {
        selecionado = true ;
        var nome=document.getElementById("nome").value;
        nomeOriginal = nome ;
        criaAjax("http://localhost:8080/PraticaDaoDataSource/Search","nome="+nome,mostrar);
    }

    function editPessoa() {
        var nome=document.getElementById("nome").value;
        var idade=document.getElementById("idade").value;
        if(idade=="" || selecionado==false){
            alert("Selecione o nome ou digite a idade!");
        }else{
            console.log("nomeOriginal: "+nomeOriginal+" para: "+nome+" idade passado: "+idade);
            criaAjax("http://localhost:8080/PraticaDaoDataSource/EditPessoa","nome="+nome+"&idade="+idade+
                    "&nomeOriginal="+nomeOriginal,mostrar);
            selecionado = false ;
            limpar();
        }    
    }
    function mostrar()
    {

        if(this.readyState===4&&this.status===200)
        {   
            var response= this.responseText ;
            
            document.getElementById("retAjax").innerHTML= response ;
        }
    }
    function limpar(){
        document.getElementById("nome").value = "";
        document.getElementById("idade").value = "";
        document.getElementById("nome").focus();
    }

window.onload = function(){
    document.getElementById("btnListar").onclick = listarDados ;
    document.getElementById("btn").onclick = mandarDados ;
    document.getElementById("btnDelete").onclick = deleteDados ;
    document.getElementById("btnSearch").onclick = pegarPessoa ;
    document.getElementById("btnEdit").onclick = editPessoa ;
}

    


