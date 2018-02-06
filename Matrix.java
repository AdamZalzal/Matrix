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
public class Matrix{
	private int[][]  matrixData;	// integer array to store integer data
	private int    rowsNum;	// number of rows
	private int    colsNum;	// number of columns

	public Matrix( int row, int col ) //constructor1
	{
            int i,j;
            rowsNum = row <= 0 ? 3 : row; //if row is less than 0 make it equal to 3
            colsNum = col <= 0 ? 3 : col;
            matrixData = new int[rowsNum][colsNum]; //allocates memory for array
            for (i = 0; i<this.rowsNum; i++) //loop that fills matrix with 0s
            {
			for(j=0; j<colsNum; j++)
                        {
                            matrixData[i][j] = 0;
                        }
            }
				
		/* constructs a row-by-col matrix with all elements equal to 0;
		if row<=0 then the number of rows of the matrix is set to 3;
		likewise, if col<=0 the number of columns of the matrix is set to 3. */
             


	}//end first constructor


	public Matrix( int[][] table) // constructor2
	{
		/* constructs a matrix out of the two dimensional array table,
		   with the same number of rows, columns, and the same element in each
		 position as array table. */

		rowsNum = table.length;
		colsNum = table[0].length;
		matrixData = new int[rowsNum][colsNum]; // allocates memory for the 2D array
		//loop to fill the array with values:
		for (int i=0; i<rowsNum; i++)
			for(int j=0; j<colsNum; j++)
				matrixData[i][j] = table[i][j];

	}//end second constructor


	public int getElement(int i, int j) throws IndexOutOfBoundsException
	{
		/* if i and j are valid indices of this matrix,
		   then the element on row i and column j of this matrix
		   is returned; otherwise it throws an exception with message "Invalid indexes".*/

		if  ((0<=i && i<=this.rowsNum) && (0<=j && j<=this.colsNum)) { //if the specified column and row are within the matrix
			return this.matrixData[i][j]; //return the value of that element
		}
		else {
			throw new IndexOutOfBoundsException("Invalid indexes."); //else throw exception
		}

	}//end getElement

	public boolean setElement(int  x, int i, int j)
	{
	      	  /* if i and j are valid indexes of this matrix, then the element on  row i and
                       column j of this matrix is assigned the value  x and true is returned;
                       otherwise false is returned and no change in the matrix is performed */
            if  ((0<=i && i<=this.rowsNum) && (0<=j && j<=this.colsNum)){ //if specified element is in matrix
                this.matrixData[i][j] = x; //set element to x
                return true;
            }
            else {
                return false; //else return false
            }
	}//end setElement

	public Matrix copy()
	{ /* returns a deep copy of this Matrix */
             
		return new Matrix(this.matrixData) ; // allocates memory for a new copy of this matrix
	}//end copy

	public void addTo( Matrix m ) throws ArithmeticException
	{
		/*adds Matrix m to this Matrix; it throws an exception message "Invalid operation"
		  if the matrix addition is not defined*/
                
		if ( (this.rowsNum == m.rowsNum) && (this.colsNum == m.colsNum)) { //if matrix m is same size as this matrix
			for (int i=0; i<rowsNum; i++) //loop through rows and columns
                            for(int j=0; j<colsNum; j++)
                            {
                               this.matrixData[i][j] = this.matrixData[i][j] + m.matrixData[i][j]; //add each element of m to this matrix
                            }

		}
		else {
			throw new ArithmeticException("Invalid operation"); //else throw exception
		}

	}

public Matrix subMatrix(int i, int j) throws ArithmeticException
{
    /*  returns a new Matrix object, which represents a submatrix of this Matrix,
    	formed out of rows 0 through i and columns 0 through j;
    	 the method should first check if values i and j are within the required range,
    	 and throw an exception if any of them is not. The exception detail message should read: "Submatrix not defined".
    	 Note: The new object should be constructed in such a way that changes in the new matrix do not affect
    	    this Matrix. */
    Matrix newmat; //initialize and allocate memory for new matrix
    newmat = new Matrix(i+1,j+1);
     if  ((0<=i && i<=this.rowsNum) && (0<=j && j<=this.colsNum)){ //if specified row and column are in matrix 
         for (int k=0; k<=i; k++) //loop through rows and columns
         {
                            for(int h=0; h<=j; h++)
                            {
                               newmat.matrixData[k][h] = this.matrixData[k][h]; //copy each element to sub matrix
                            }

         }
     }
     else{
         throw new ArithmeticException("Submatrix not defined"); //throw exception
     }
     return newmat;
    
    

}




	public String toString()
	{
		/* returns a string representing the matrix,
		   with each row on a line, and the elements in each row being separated by 2 blank spaces. */

		String output = new String(); // creates an empty string
        	for(int i = 0; i < rowsNum; i++){ //loops through rows
        		for(int j = 0; j < colsNum; j++){ //loops through solumns
        			output += matrixData[i][j] + "  "; //stores all matrix data in ouput variable
        		}
        	output += "\n"; //adds new line
        	}
       		 return output; //returns output

	}//end toString

	// write the remaining methods
      public int Row()
      {
          return this.rowsNum; //returns number of rows
      }
      
      public int Col()
      {
          return this.colsNum; //returns number of columns
      }
      
      public boolean isUpperTr()
      {
          int i,j;
          for(i=1;i<this.rowsNum;i++) //loop through rows
          {
              for(j=0; j<i;j++) //loop through columns to i
              {
                if(this.matrixData[i][j]!=0) //if element is nonzero then return false
                {
                    return false;
                }
              }
          }
          return true; //return true
      }
      
      public static Matrix sum(Matrix[] matArray)
      {
          Matrix sum; //initialize and allocate memory for new matrix the size of the first matrix
          sum = new Matrix(matArray[0].rowsNum,matArray[0].colsNum);
          for(int i=0;i<matArray.length;i++) //loop through size of array of matrices
          {
              sum.addTo(matArray[i]); //add each matrices to sum
          }
          return sum;
      }
}//end class