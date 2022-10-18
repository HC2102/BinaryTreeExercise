public class BinaryTree {
    Node root;
    //check if empty
    public boolean isEmpty(){
        return root ==null;
    }
    //clear the tree
    public void clear(){
        root = null;
    }
    //insert node
    public void insert(int value){
        //new node
        Node newNode = new Node(value);
        //if tree is empty --> add to the root
        if(isEmpty()){
            root = newNode;
        }
        //else, traverse to the right position
        else{
            //focus to determine which side the new value will go
            Node focus = new Node();
            //pointer to traverse from root to leaf to reach the proper position
            Node pointer = root;
            while(pointer!=null){ //traverse until reach the dead end
                focus = pointer;
                if(focus.value == value) { // in case value is already exits in the tree --> discard it
                    System.out.println("Value already exits, discard");
                    return;
                }else{
                    //if new node value > focus, move right
                    if(pointer.value < value){
                        pointer = pointer.right;
                    }else{
                        //else move left
                        pointer = pointer.left;
                    }
                }
            }
            //insert node using focus (now pointer reach the null)
            if(focus.value < value){
                focus.right = newNode;
            }else{
                focus.left = newNode;
            }
        }
    }
    //search
    public Node search(int value){
        Node pointer = root;
        while(pointer != null){
            if(pointer.value == value){
                return pointer;
            }
            else{
                //move
                if(pointer.value < value){
                    pointer = pointer.right;
                }else{
                    pointer = pointer.left;
                }
            }
        }
        return null;
    }
    //breadth first search
    public void breadth(){
        int h = height(root); //height start from root
        int i;
        for(i = 1; i<h; i++){
            printCurrentLevel(root,i);
        }
    }
    //find height
    public int height(Node root){
        if(root == null) return 0; //if tree is empty then height is o
        else{
            int lheight = height(root.left); //left height
            int rheight = height(root.right); //right height
            if(lheight > rheight)
            {
                return lheight +1;
            }else{
                return rheight +1;
            }
        }
    }
    //print current level
    public void printCurrentLevel(Node root, int level){
        if(root == null) return;
        if(level==1) System.out.println(root.value+" ");
        else if(level>1){
            printCurrentLevel(root.left,level-1);
            printCurrentLevel(root.right, level-1);
        }
    }
    //depth search order
    public void inOrder(Node p){
        if(p == null ) return;
        inOrder(p.left);
        System.out.println(p.value);
        inOrder(p.right);
    }
    public void preOrder(Node p){
        if(p == null ) return;
        System.out.println(p.value);
        preOrder(p.left);
        preOrder(p.right);
    }
    public void posOrder(Node p){
        if(p == null ) return;
        posOrder(p.left);
        posOrder(p.right);
        System.out.println(p.value);
    }
    public int count(Node p){
        if(p == null){
            return 0;
        }
        //recursive call to left child and right child
        //add the result of these wth 1 (1 for counting the root)
        return 1+count(p.left)+count(p.right);
    }
    //!
    public int max(Node p){
        if( p ==null){
            return -1;
        }
        int res = p.value;
        int lres = max(p.left);
        int rres = max(p.right);
        if(lres>res){
            res = lres;
        }else{
            res = rres;
        }
        return res;
    }
   //!
    public int min(Node p){
        if( p ==null){
            return -1;
        }
        int res = p.value;
        int lres = min(p.left);
        int rres = min(p.right);
        if(lres<res){
            res = lres;
        }else{
            res = rres;
        }
        return res;
    }
//    public Node deleteRecursive(Node root, int key){
//
//    }
    //sum
    public int sum(Node root){
        if(root ==null){
            return 0;
        }return(root.value + sum(root.left)+sum(root.right));
    }
    public double avg(Node root){
        return((double)sum(root)/(double)count(root));
    }

    public boolean isBalance(Node node){
        int lh, rh;
        if(node == null){
            return true;
        }
        lh = height(node.left);
        /* Get the height of left and right subtrees */
        rh = height(node.right);
        if(Math.abs(lh-rh)<=1&& isBalance(node.left)&& isBalance(node.right)){
            return true;
        }
        /* If we reach here then tree is not height-balanced
         */
        return false;
    }
}
