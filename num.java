class num
{
    public static void toWords(int num)
    {
        int i,l=length(num),d;
        String s="";boolean c=false;
        String r[]=new String[9];if(num==0)s="Zero";
        for(i=0;i<9;i++)r[i]="";
        r[7]="Crore ";r[5]="Lakh ";r[3]="Thousand ";r[2]="Hundred ";
        for(i=l;i>=0;i--)
        {
            d=num/(int)Math.pow(10,i);
            if((i%2!=0&&i!=1)||i==2||i==0)
            {   
                if(!c)
                {
                    switch(d)
                    {
                        case 1 :s+="One ";break;
                        case 2 :s+="Two ";break;
                        case 3 :s+="Three ";break;
                        case 4 :s+="Four ";break;
                        case 5 :s+="Five ";break;
                        case 6 :s+="Six ";break;
                        case 7 :s+="Seven ";break;
                        case 8 :s+="Eight ";break;
                        case 9 :s+="Nine ";break;
                        default:s+="";
                    }
                }
                else
                {c=false;
                    switch(d)
                    {
                        case 1 :s+="Eleven ";break;
                        case 2 :s+="Twelwe ";break;
                        case 3 :s+="Thirteen ";break;
                        case 4 :s+="Fourteen ";break;
                        case 5 :s+="Fifteen ";break;
                        case 6 :s+="Sixteen ";break;
                        case 7 :s+="Seventeen ";break;
                        case 8 :s+="Eighteen ";break;
                        case 9 :s+="Nineteen ";break;
                        case 0:s+="Ten";break;
                        default:s+="";
                    }
                }
            }
            else
            {
                switch(d)
                {
                    
                    case 1 :c=true;break;
                    case 2 :s+="Twenty ";break;
                    case 3 :s+="Thirty ";break;
                    case 4 :s+="Fourty ";break;
                    case 5 :s+="Fifty ";break;
                    case 6 :s+="Sixty ";break;
                    case 7 :s+="Seventy ";break;
                    case 8 :s+="Eighty ";break;
                    case 9 :s+="Ninety ";break;
                    default:s+="";
                }
            }
            s=s+r[i];
            num%=Math.pow(10,i);
        }
        System.out.println(s);
    }
    static int length(int n)
    {
        if(n==0)return -1;
        return 1+length(n/10);
    }
}
                