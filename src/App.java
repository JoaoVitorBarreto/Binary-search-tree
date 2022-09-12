import java.util.Scanner;
import java.util.Vector;

// CLASSE DE TESTE

public class App {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        Boolean teste = true;
        Arvore arv = new Arvore();
            
        while(teste==true){
            System.out.println("1) insert(key, value) \n2) remove(key)"
                             +"\n3) getRoot() \n4) find(key, no)"
                             +"\n5) replace(key, value) \n6) depth(no)"
                             +"\n7) height() \n8) isEmpty()"
                             +"\n9) nos() \n10) elements()"
                             +"\n11) mostrar - matrix()\n12) parent(no)"
                             +"\n13) size"
                             );                
            switch(input.nextInt()){
                case 1: // insert
                    System.out.println("Insira a chave e o valor do item");    
                    try{
                        arv.insert(input.nextInt(), input.nextInt());
                    }        
                    catch(Exception e){
                        System.out.println(e);
                    }            
                    
                    break; 
                case 2: // remove
                    System.out.println("Insira a chave do item a ser removido");
                    try{
                        System.out.println(arv.remove(input.nextInt()).getKey());
                    }
                    catch(Exception e){
                        System.out.println(e);
                    }                    
                    break;
                case 3: // getRoot
                    try{
                        System.out.println("Chave de root "+arv.getRoot().getKey());
                    }
                    catch(Exception e){
                        System.out.println(e);
                    }                    
                    break;
                case 4: // find
                    System.out.println("Insira a chave que deseja buscar: ");
                    try{
                        System.out.println((arv.find(input.nextInt(), arv.getRoot())).getValue());
                    }
                    catch(Exception e){
                        System.out.println(e);
                    }                    
                    break;
                case 5: // replace
                    System.out.println("Insira a chave do item existente e novo valor: ");
                    try{
                        arv.replace(input.nextInt(), input.nextInt()); 
                    }
                    catch(Exception e){
                        System.out.println(e);
                    }
                    break;
                case 6: // depth
                    try{
                        System.out.println("Chave do nó que deseja saber profundidade: ");
                        No no = arv.find(input.nextInt(), arv.getRoot());
                        System.out.println(arv.depth(no));
                    }
                    catch(Exception e){
                        System.out.println(e);
                    }
                    break;
                case 7: // height
                    System.out.println(arv.height());
                    break;
                case 8: // isEmpty
                    System.out.println(arv.isEmpty());
                    break;
                case 9: // nos
                    Vector<No> v = arv.nos();
                    for(int i=0; i<v.size(); i++){
                        No n = v.get(i);
                        if(n == null){
                            continue;
                        }
                        System.out.println("Chave: "+n.getKey());
                        System.out.println("Valor: "+n.getValue());
                        System.out.println("Chave do filho esquerdo: "+(n.getLeftChild() != null ? n.getLeftChild().getKey() : "sem filho esquerdo"));
                        System.out.println("Chave do filho direito: "+(n.getRightChild() != null ? n.getRightChild().getKey() : "sem filho direito"));
                        System.out.println("Chave do pai: "+(n.getParent() != null ? n.getParent().getKey() : "sem pai"));
                    }
                    break;

                case 10: // elements
                    Vector<Integer> elementos = arv.elements();
                    for(int i=0; i<elementos.size(); i++){
                        System.out.println(elementos.get(i));
                    }
                    break;
                case 11: // mostrar
                    System.out.println("");
                    arv.matrix();
                    System.out.println("");
                    break;
                case 12: // parent
                    System.out.println("Chave do nó do qual deseja saber o pai: ");
                    try{
                        No x = arv.find(input.nextInt(), arv.getRoot());
                        No parent = x.getParent();
                        if(parent != null){
                            System.out.println(parent.getKey());
                        }
                        else{
                            System.out.println("Não tem pai");
                        }                        
                    }
                    catch(Exception e){
                        System.out.println(e);
                    }
                    
                    break;
                case 13: // size
                    System.out.println(arv.size());
                    break;
                }   
        }
        input.close();
    }  
}
