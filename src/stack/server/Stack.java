package stack.server;


class Stack {
    
   
    private int[] stk = new int[10];
    private int st = 10;  
    
    void push(int v){
        if(st != 0){
            st = st - 1; 
            stk[st] = v;
        }
    }
    
    int pop(){
        int temp = -1;
        if(st != 10){
            temp = stk[st];
            st = st + 1;
        }
        return temp;
    }
    
    @Override
    public String toString(){
        String str = "::"; 
        for(int i = st ; i < 10 ; i++){
               str = str + stk[i] + "::";
        }
        if(isFull())return "full,"+str;
        if(isEmpty())return "empty,"+str;
        return "normal,"+str;        
    }
    
    public boolean isFull(){
        return st==0;
    }
    
    public boolean isEmpty(){
        return st==10;
    }
}