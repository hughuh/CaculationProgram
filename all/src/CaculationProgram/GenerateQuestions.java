package CaculationProgram;
import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class GenerateQuestions {
    private static Random random = new Random();
//    public static int range;
    public static String judge(int a,int b) {//判断是否为假分数，如是则化为真分数
        if(a >= b) {
            int c,d;
            c = a/b;
            d = a % b;//算a除b的余数
            if(d == 0) //刚好整除时返回整数，为保持结构完整同时返回" "
                return c+"";
            else
                return c+"’"+d+"/"+b;//格式，例：5'7/8
        }
        return a + "/" + b ;
    }
    public static String fraction_process(int a,int b) {//处理分数，
        int y = 1;
        for (int i = a; i >= 1; i--) {
            if (a % i == 0 && b % i == 0) {
                y = i;
                break;
            }
        }
        int z = a / y;// 分子
        int m = b / y;// 分母
        if (z == 0) {
            return "0";
        }
        if(m==1) return z+"";
        else  return judge(z,m);

    }

    public static void generate_questions(int num_question,int num_range) {//主要调用函数
        String[] results = new String[num_question];
        for(int i = 0;i<num_question;i++) {
            String[] question_arry = new String[2];//生成数组储存题目
            int a= (int) (random.nextInt(num_range));//分子
            int b= (int) (random.nextInt(num_range));//分母
            int c= (int) (random.nextInt(num_range));//另一个分子
            int d= (int) (random.nextInt(num_range));//另一个分母
            int characters ;
            characters = (int)(random.nextInt(4));
            if(b!=0&&d!=0) {//分母均不为0时生成带有分数的计算题，同时计算结果
                if(characters==0) {
                    int fenzi=a*d+b*c;
                    int fenmu=b*d;
                    question_arry[0]=judge(a,b)+' '+ '+'+' '+judge(c,d)+' '+'='+' ';
                    System.out.println(question_arry[0]);
                    results[i]=fraction_process(fenzi, fenmu);

                }
                if(characters==1&&a*d-b*c>=0) {
                    int fenzi=a*d-b*c;
                    int fenmu=b*d;
                    question_arry[0]=judge(a,b)+' '+'-'+' '+judge(c,d)+' '+'='+' ';
                    System.out.println(question_arry[0]);
                    results[i]=fraction_process(fenzi, fenmu);

                }
                if(characters==1&&a*d-b*c<0) {
                    int fenzi=b*c-a*d;
                    int fenmu=b*d;
                    question_arry[0]=judge(a,b)+' '+'-'+' '+judge(c,d)+' '+'='+' ';
                    System.out.println(question_arry[0]);
                    results[i]=fraction_process(fenzi, fenmu);

                }
                if(characters==2) {
                    int fenzi=a*c;
                    int fenmu=b*d;
                    question_arry[0]=judge(a,b)+' '+'×'+' '+judge(c,d)+' '+'='+' ';
                    System.out.println(question_arry[0]);
                    results[i]=fraction_process(fenzi, fenmu);

                }
                if(characters==3&&c!=0) {
                    int fenzi=a*d;
                    int fenmu=b*c;
                    question_arry[0]=judge(a,b)+' '+'÷'+' '+judge(c,d)+' '+'='+' ';
                    System.out.println(question_arry[0]);
                    results[i]=fraction_process(fenzi, fenmu);

                }
                if(characters==3&&c==0) {
                    break;
                }

            }
            else {//分母至少一个为0时生成只含有整式的运算式，同时计算结果
                b=1; d=1;
                if(characters==0) {
                    int fenzi=a*d+b*c;
                    int fenmu=b*d;
                    question_arry[0]=a+' '+"+"+' '+c+' '+"="+' ';
                    System.out.println(question_arry[0]);
                    results[i]=fraction_process(fenzi, fenmu);

                }
                if(characters==1&&a*d-b*c>=0) {
                    int fenzi=a*d-b*c;
                    int fenmu=b*d;
                    question_arry[0]=a+' '+"-"+' '+c+' '+"="+' ';
                    System.out.println(question_arry[0]);
                    results[i]=fraction_process(fenzi, fenmu);

                }
                if(characters==1&&a*d-b*c<0) {
                    int fenzi=b*c-a*d;
                    int fenmu=b*d;
                    question_arry[0]=c+' '+"-"+' '+a+' '+"="+' ';
                    System.out.println(question_arry[0]);
                    results[i]=fraction_process(fenzi, fenmu);

                }
                if(characters==2) {
                    int fenzi=a*c;
                    int fenmu=b*d;
                    question_arry[0]=c+' '+"×"+' '+a+' '+"="+' ';
                    System.out.println(question_arry[0]);
                    results[i]=fraction_process(fenzi, fenmu);

                }
                if(characters==3&&c!=0) {
                    int fenzi=a*d;
                    int fenmu=b*c;
                    question_arry[0]=a+' '+"÷"+' '+c+' '+"="+' ';
                    System.out.println(question_arry[0]);
                    results[i]=fraction_process(fenzi, fenmu);

                }
                if(characters==3&&c==0) {
                    break;

                }

            }

            //将题目写入文件
            FileWriter doc1 = null;
            try {

                File f=new File("exercise.txt");//题目写入
                doc1 = new FileWriter(f, true);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(question_arry[0]!=null)
            {
                PrintWriter pw = new PrintWriter(doc1);
                pw.println(i+1+"."+question_arry[0]);
                pw.flush();
                try {
                    doc1.flush();
                    pw.close();
                    doc1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }//if循环结束

            FileWriter doc2 = null;
            try {

                File f=new File("answer.txt");//答案写入
                doc2 = new FileWriter(f, true);
            } catch (IOException e) {
                e.printStackTrace();
            }if(question_arry[0]!=null) {
                PrintWriter pn = new PrintWriter(doc2);
                pn.println(i+1+"."+' '+results[i]);
                pn.flush();
                try {
                    doc2.flush();
                    pn.close();
                    doc2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }//for循环结束
    }
}

