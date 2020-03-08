/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.practica1compiladorer;

/**
 *
 * @author Dulfary
 */
public class ToDeterministic {
    private Thompson thompson;

    public ToDeterministic(Thompson thompson) {
        this.thompson = thompson;
    }
    
    public int setNodeNames(Node n,int futureName){
        if(n.getName()==0){
            n.setName(futureName);
            if(n.getLeftLink()!=null){
                System.out.println("Lado izquierdo");
                futureName++;
                futureName = this.setNodeNames(n.getLeftLink(),futureName);
            }
            if(n.getRightLink()!=null){
                System.out.println("Lado derecho");
                futureName++;
                futureName = this.setNodeNames(n.getRightLink(), futureName);
            }
        }else{
            futureName--;
        }
        return futureName;
    }
    
    public void travelNull(){
    
    }
    
    
}
