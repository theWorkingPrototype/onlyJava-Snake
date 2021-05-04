//only for Snake_Xenxia.
class Node
{
    int x,y;boolean b;
    Node(int x1,int y1,boolean b1)
    {
        x=Snake_Xenxia.check(x1,true);
        y=Snake_Xenxia.check(y1,false);
        b=b1;
    }
}