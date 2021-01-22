package Logic.Data.Data;

import java.io.Serializable;

public class Crew implements Serializable{
     private boolean vivo;
    private String nome;
    
    public Crew(String nome){
        this.nome=nome;
        this.vivo=true;
    }
    
    public boolean getVivo(){
        return vivo;
    }
    
    public void setVivo(boolean vivo){
        this.vivo = vivo;
    }
    
    public String getNome(){
        return nome;
    }
    
    @Override
    public String toString(){
        String str = "";
        
        str += nome;
        if(vivo)
            str += " (Vivo)";
        else str += " (Morto)";
        
        return str;
    }
}
