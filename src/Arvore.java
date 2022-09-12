import java.util.Vector;

public class Arvore {
    private No root;

    public No getRoot() throws Exception{
        if(this.root == null){
            throw new Exception("Árvore vazia");
        }
        return this.root;
    }

    public boolean isExternal(No no){
        if((no.getRightChild() == null && no.getLeftChild() == null) ){
            return true;
        }
        return false;
    }

    public boolean isInternal(No no){
        return !this.isExternal(no);
    }
    
    public boolean isEmpty(){
        if(this.root==null){
            return true;
        }
        return false;
    }

    public No find(int key, No no){
        if((no == null) || (no.getKey() == key)){
            return no;
        }
        if(key < no.getKey()){
            return find(key, no.getLeftChild());
        }
        else if(key > no.getKey()){
            return find(key, no.getRightChild());
        }
        return null;
    }
    
    public void insert(int key, int value) throws Exception{
        No temp = find(key, this.root);
        if(temp != null){
            throw new Exception("Chave já existe.");
        }
        else{
            No x = this.root;
            No y = null;

            No novoNo = new No(key, value);

            while(x != null){
                y = x;
                if(key < x.getKey()){
                    x = x.getLeftChild();
                }
                else{
                    x = x.getRightChild();
                }
            }

            if(y == null){
                y = novoNo;
                this.root = y;
            }

            else{
                if(key < y.getKey()){
                    y.setLeftChild(novoNo);                
                }
                else{
                    y.setRightChild(novoNo);
                }
                novoNo.setParent(y); 
            }      
        }
    }

    public No remove(int key) throws Exception{
        No no = this.find(key, this.root);
        if(no == null){
            throw new Exception("Chave inexistente");
        }
        else{            
            No pai = no.getParent();
            if(this.isExternal(no)){  
                if(no == this.root){
                    this.root = null;
                }              
                else if(key < pai.getKey()){
                    pai.setLeftChild(null);
                }
                else{
                    pai.setRightChild(null);
                }                
                no.setParent(null);
            }
            else{                
                if((no.getLeftChild() != null) && (no.getRightChild() == null)){
                    if(this.root == no){
                        this.root = no.getLeftChild();
                        no.getLeftChild().setParent(null);
                    }
                    else{
                        no.getLeftChild().setParent(pai);
                        if(key < pai.getKey()){
                            pai.setLeftChild(no.getLeftChild());
                        }
                        else{
                            pai.setRightChild(no.getLeftChild());
                        }  
                    }      
                    no.setParent(null);
                    no.setLeftChild(null);              
                }
                else if((no.getLeftChild() == null) && (no.getRightChild() != null)){
                    if(this.root == no){
                        this.root = no.getRightChild();
                        no.getRightChild().setParent(null);
                    }
                    else{
                        no.getRightChild().setParent(pai);
                        if(key < pai.getKey()){
                            pai.setLeftChild(no.getRightChild());
                        }
                        else{
                            pai.setRightChild(no.getRightChild());
                        }
                    }                    
                    no.setParent(null);
                    no.setRightChild(null);
                }
                else{
                    No x = no.getRightChild();
                    No y = null;

                    while(x != null){
                        y = x;
                        x = x.getLeftChild();
                    }

                    if(this.root == no){                        
                        no.getLeftChild().setParent(y);
                        if(this.root != y.getParent()){
                            y.getParent().setLeftChild(null);
                            if(y.getRightChild()!= null){
                                y.getRightChild().setParent(y.getParent());
                                y.getParent().setLeftChild(y.getRightChild());
                            }
                            y.setRightChild(no.getRightChild()); 
                            no.getRightChild().setParent(y);  
                        }   
                        y.setParent(null);
                        y.setLeftChild(no.getLeftChild());
                        this.root = y;                         
                    }
                    else{
                        if(key < pai.getKey()){
                            pai.setLeftChild(y);
                        }
                        else{
                            pai.setRightChild(y);
                        }     

                        if(no.getRightChild() != y){
                            y.getParent().setLeftChild(null);
                            if(y.getRightChild()!= null){
                                y.getRightChild().setParent(y.getParent());
                                y.getParent().setLeftChild(y.getRightChild());
                            }
                            y.setRightChild(no.getRightChild());
                            y.getRightChild().setParent(y);
                        }
                        y.setParent(pai);
                        y.setLeftChild(no.getLeftChild());                        
                        y.getLeftChild().setParent(y);

                        no.setParent(null);
                        no.setLeftChild(null);
                        no.setRightChild(null);
                    }
                }
            }
        }
        return no;
    }

    public void replace(int key, int value) throws Exception{
        No no = this.find(key, this.getRoot());
        if(no == null){
            throw new Exception("Chave inexistente");
        }
        no.setValue(value);
    }

    public int size(){
        return size2(this.root);
    }

    // método chamado internamente para ser usado recursivamente
    private int size2(No no){
        if(no == null){
            return 0;
        }
        return (this.size2(no.getLeftChild()) + 1 + this.size2(no.getRightChild()));
    }

    public boolean isRoot(No no){
        if(no == this.root){
            return true;
        }
        return false;
    }

    public No parent(No no){
        return no.getParent();
    }

    public No[] children(No no){
        No[] nos = new No[2];
        nos[0] = no.getLeftChild();
        nos[1] = no.getRightChild();
        return nos;        
    }

    public int depth(No no) throws Exception{
        if(no == null){
            throw new Exception("Nó inexistente");
        }
        if(this.isRoot(no)){
            return 0;
        }
        else{
            return 1+this.depth(no.getParent());
        }
    }

    public int height(){  
        return height2(this.root);
    }

    private int height2(No no){
        if(no == null){
            return 0;
        }
        if(this.isExternal(no)){
            return 0;
        }
        else{
            int h = 0;
            for(int i=0; i<2; i++){
                No[] filhos = this.children(no);
                h = Math.max(h, this.height2(filhos[i]));
            }
            return h+1;
        }
    }

    public Vector<Integer> elements(){
        Vector<Integer> elementos = new Vector<Integer>(this.size());
        if(this.isEmpty()){
            return elementos;
        }
        this.elements2(this.root, elementos);
        return elementos;
    }

    private void elements2(No no, Vector<Integer> elementos){
        if(no.getLeftChild() != null){            
            this.elements2(no.getLeftChild(), elementos);            
        }
        elementos.add((int)no.getValue());
        if(no.getRightChild() != null){
            this.elements2(no.getRightChild(), elementos);
        }
    }

    public Vector<No> nos(){
        Vector<No> vetor = new Vector<No>(this.size());
        if(this.isEmpty()){
            return vetor;
        }
        this.nos2(this.root, vetor);
        return vetor;
    }

    private void nos2(No no, Vector<No> vetor){
        if(no.getLeftChild() != null){            
            this.nos2(no.getLeftChild(), vetor);            
        }
        vetor.add(no);
        if(no.getRightChild() != null){
            this.nos2(no.getRightChild(), vetor);
        }
    }
    
    public void matrix() throws Exception{
        int linhas = this.height()+1;
        int colunas = this.size();
        Vector<No> vetor = this.nos();
        
        String[][] matriz = new String[linhas][colunas]; 

        for(int i=0; i<colunas; i++){
            No no = vetor.get(i);
            matriz[this.depth(no)][i] = Integer.toString(no.getKey());
        }

        for(int l=0; l<linhas; l++){
            for(int c=0; c<colunas; c++){
                System.out.print((matriz[l][c] == null ? "" : matriz[l][c])+"\t");
            }
            System.out.println("");
        }
    }
}