/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrix;

/**
 *
 * @author adamzalzal
 */
public class UpperTriangularMatrix {
    private int size;
    private int data[];
    
    public UpperTriangularMatrix(int n)
    {   
        int length; 
        if(n<=0) //if n is less than or equal to 0
        {
            n = 1; //set to 1
        }
      
        size = n; //store size of matrix
        length= n*(n+1)/2; //calculate length of efficent storage array
        data = new int [length]; //new array of size length
        
    }
    public UpperTriangularMatrix(Matrix upTriM) throws IllegalArgumentException 
    {
        int count = 0; 
        if(upTriM.isUpperTr()) //if input matrix is upper triangular
        {
        int length = upTriM.Row(); //store size of matrix
        
        size = length; //assign size to this array
        data = new int [size*(size+1)/2]; //allocate memory for size of efficient array
        for(int i=0;i<size;i++) //for size of this array
        {
            
            for(int j = i; j<size;j++) //for size of this array where j = i
            {
            data[count] = upTriM.getElement(i, j); //assign this array at 'count' to element of input matrix
            count++; //increment count
            }
        }
        }
        else{ //else throw exception
            throw new IllegalArgumentException("Matrix is not upper triangular");
        }
    }
    public int getDim() 
    {
        return this.size; //return size of matrix
    }
    public int getElement(int i,int j) throws IndexOutOfBoundsException
    {
        int n = this.size; //stroe size of UTM
        int pos = 0,k; //initialize position and counter
        if((i>=0 && i<this.size) && (j>=0 && j<this.size)) //if specified element is in matrix
        {   
            if(j<i) //if column number is less that row number
            {
                return 0; //element is a zero
            }
            else{
                if(i==0){ //if row number is 0 return the element of this array with index of the column number
                    return this.data[j];
                }
                else{ //else loop through number of rows
                    for(k=0; k<i;k++)
                    pos = pos + (n-k); //position is postion plus n-1,n-2,n-3,.., to n-(i-1)
                } //current position of array is equivelant to the element at the end of the 'i'th row
                return this.data[pos+(j-i)]; //subtract difference between column# and row# from current index
            }
        }
        else{ //else throw exception
            throw new IndexOutOfBoundsException("Invalid Index");
        }
    }
    public int setElement(int x, int i,int j) throws IndexOutOfBoundsException,IllegalArgumentException
    {
        int pos=0,k,n=this.size;
        if((i>=0 && i<this.size) && (j>=0 && j<this.size)) //if element is in matrix
        {
            if(j<i) //if column number is less than row number
            {
                throw new IllegalArgumentException("Incorrect Argument"); //elemen is 0 and cannot be changed
            }
            if(i==0){
                    return this.data[j] = x; //if row is first row return element of this array with index j
                }
                else{
                    for(k=0; k<i;k++) //loop through rows
                    pos = pos + (n-k);  //position is postion plus n-1,n-2,n-3,.., to n-(i-1)
                }//current position of array is equivelant to the element at the end of the 'i'th row
                return this.data[pos+(j-i)] = x; //subtract difference between column# and row# from current index
                //set this index to x
            }
        
        else{
            throw new IndexOutOfBoundsException("Invalid Index"); //else throw exception
        }
    }
    public Matrix fullMatrix()
    {   
        Matrix newmat = new Matrix(this.size,this.size); //initialize and allocate memory for new matrix of equivalent size
        int i,j; //initialize counters
        for( i = 0; i<this.size;i++) //loop through rows of this matrix
        {
            for( j = 0; j<this.size;j++ )  //loop through columns of this matrix
            {
                newmat.setElement(this.getElement(i, j), i, j); //set each element to its equivalent element in the efficient array
            }
        }
        return newmat; //return new matrix
    }
    public void print1DArray()
    {
        int length = this.size*(this.size+1)/2; //store length of efficient array
        for(int i = 0; i<length; i++) //loop through efficient array
        {
            System.out.printf("%d  ", this.data[i]); //print each element followed by two spaces
        }
    }
   
    public String toString()
    {
        String output = new String(); // creates an empty string
        	for(int i = 0; i < this.size; i++){ //loops through rows
        		for(int j = 0; j < this.size; j++){ //loops through columns
        			output += this.getElement(i, j) + "  "; //fills empty string with each element
        		}
        	output += "\n"; //adds new line
                }
                return output;
    }
    public int getDet()
    {
        int product = 1; //sets product to 1
        for(int i = 0;i<this.size;i++) //loops through size of array
        {
            product = product * this.getElement(i, i); //finds product of main diagonal
            
        }
        return product; //returns product
    }
  double[] effSolve(double[] b) throws IllegalArgumentException
    {   
        double x[] = new double[b.length]; //initialize and allocates memory for new matrix
        int pos = this.size*(this.size+1)/2;
        
        if(b.length != this.size) //if b is not same size as this matrix
        {
            throw new IllegalArgumentException("Incorrect Argument"); //throw exception
        }
        if(this.getDet()==0) //if the determinant of this matrix is 0
        {
            throw new IllegalArgumentException("Determinant of A is 0."); //throw exception
        }
        //using back substitution to find x
        for(int i = b.length-1;i>=0;i--) //for looping backwards through length of b
        {
           x[i] = b[i]; //assigning b to x
         
              pos = pos - (b.length-i);
          

           for(int j = i+1; j<b.length;j++) //nested for loop
           {
               x[i] = x[i] - this.data[pos+(j-i)] * x[j]; // x[i] is equalt to b minus the sum of the elemnts on the 'i'th row times x at j
               
           }
           x[i] = x[i]/this.data[pos]; //the expression from the line above divided by the 'i'th row diagonal
        }
        return x; //return x
    }
}