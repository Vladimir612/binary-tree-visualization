<h1>Binary tree visualization</h1>

Our task is to display binary tree in console like this

![image](https://user-images.githubusercontent.com/53167193/170790789-02ecf273-7de4-4ced-a8d9-9b10f6b9923a.png)

Tree class(It also has all necessary functions inside it. I will explain in a minute why are there matrixTree and treeDepth.):

![image](https://user-images.githubusercontent.com/53167193/170791886-1bcfdfc7-9142-4011-a7ac-0b103cb6b787.png)

Tree node class: 

![image](https://user-images.githubusercontent.com/53167193/170791918-9ff4f61d-65b1-488e-8851-0bfa49d43c2d.png)

First we need to fill our tree with data in memory. We're gonna use addBst function, it looks like this:

![image](https://user-images.githubusercontent.com/53167193/170790908-5fc94e6d-8edb-4400-bb0e-fe9d5d95763b.png)

This function makes our tree sorted when we look at it from left to right. If number that we passed to function is bigger than number on current node and if that node doesn't have right child, wre will make him the right child and the number on him will be the passed number, if it already have right child we will call recursion and pass that right child. The same goes for the left child if the number is smaller than number on current node.

We will need addBstArr function so we can pass array of numbers and add it to our tree.

![image](https://user-images.githubusercontent.com/53167193/170791554-30898a05-c1a5-440e-a16b-98c2ca4abde1.png)

Our main function looks like this: 

![image](https://user-images.githubusercontent.com/53167193/170791711-18aaf8f0-34cb-4785-a7b5-f42aa57d867b.png)

When we call print function we want to do more things. First of all easiest way to print this tree as we want is to use matrix:

![image](https://user-images.githubusercontent.com/53167193/170796332-a64a1d7e-abad-42e1-a849-a89c4b76c39d.png)

Now we have to find logic how to put tree nodes inside of this matrix but to work in every case

First lets create empty matrix, the number of rows is tree depth, the number of columns is 2^(tree depth) - 1. When matrix is "empty" we want that every element of
this matrix has Integer minimal value and later we will not print those fields. The reason why are matrix has TreeNodes instead of integers is because it will make our
job easier later. I will explain that in our formMatrix function.

Our print function is actually 4 steps(functions) that will do our whole job. First we must get tree depth, then we form our empty matrix, then we form our final matrix and then we print it:

![image](https://user-images.githubusercontent.com/53167193/170797477-3b002bfe-afd8-4e58-bdd6-c7c7ca7cdc4f.png)

getTreeDepth function: 

![image](https://user-images.githubusercontent.com/53167193/170797575-bc12f411-e1e4-4137-a6cb-459fd50eb18f.png)

We will calculate every time the size of depth of the right child and the size of depth of the left child and then return one step upwards and add one to bigger
value.

formEmptyMatrix function:

![image](https://user-images.githubusercontent.com/53167193/170797976-79651459-2dac-445a-b297-056ea519d25c.png)

Like I said earlier we set the appropriate size for the matrix and set the minimal integer value to the members.

Now the most important logic. formMatrix function:

![image](https://user-images.githubusercontent.com/53167193/170798189-7e130301-be60-4bc8-bdc5-687b2017a8cc.png)

We will go row by row. The root element has maximum depth every time, the other nodes have a depth that is one less than the above. That is one rule. If we are on root
element(i == 0) we want to put it in the middle of first row(the row is 0(i), and the column is (2^treeDepth - 1) / 2). In every other case when we are on some other
node we want to put it on left side of the parent(if he is left child) or to the right side. We are going through whole row and when we arrive here:

![image](https://user-images.githubusercontent.com/53167193/170801245-aec6f2e3-b768-4c41-9ed7-14ac3bb484d4.png)

we ask the parent(in this case the root), if passed node is the parent left child or right child we will put it in matrix on his left side or right side. The math to know the exact spot:

The row will be i, the column will be j - (2^(currentDepth of node - in our case its 3) / 2) if its left child and j + (2^(currentDepth of node) / 2) if its right 
child

The picture behind that math:

![image](https://user-images.githubusercontent.com/53167193/170801969-f5e25665-a16e-42dc-b523-ea84b6f77a44.png)

j position is 7 when we put in matrix element 2 and after math we will put it on coordinates (1, 3). The same thing goes for right children but only with plus 
operation.

We call recursion so we can go thrugh whole tree, when we go to the null nodes that's our end case.

So the reason why we put TreeNodes in matrix is that so when we put children in matrix we can ask his parent if it is his left or right child and based on the position
of the parent we can easily adjust the position of the child.

Now lets print this matrix:

![image](https://user-images.githubusercontent.com/53167193/170802265-d45c1aea-8577-4c63-a81d-c30743820151.png)

When we are on elements that has minimal integer value we want to print blank spaces. When we are on elements that are real numbers we want to print them. If they are
negative we want to print without helping space but if they are positive we want another blank space before number. We do that only for the alignment of the tree when
we have negative and positive values.

We printed our tree!
