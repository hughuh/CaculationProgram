package CaculationProgram;
import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class GenerateQuestions {
    private static Random random = new Random();
//    public static int range;
    public static String judge(int a,int b) {//�ж��Ƿ�Ϊ�ٷ�����������Ϊ�����
        if(a >= b) {
            int c,d;
            c = a/b;
            d = a % b;//��a��b������
            if(d == 0) //�պ�����ʱ����������Ϊ���ֽṹ����ͬʱ����" "
                return c+"";
            else
                return c+"��"+d+"/"+b;//��ʽ������5'7/8
        }
        return a + "/" + b ;
    }
    public static String fraction_process(int a,int b) {//���������
        int y = 1;
        for (int i = a; i >= 1; i--) {
            if (a % i == 0 && b % i == 0) {
                y = i;
                break;
            }
        }
        int z = a / y;// ����
        int m = b / y;// ��ĸ
        if (z == 0) {
            return "0";
        }
        if(m==1) return z+"";
        else  return judge(z,m);

    }

    public static void generate_questions(int num_question,int num_range) {//��Ҫ���ú���
        String[] results = new String[num_question];
        for(int i = 0;i<num_question;i++) {
            String[] question_arry = new String[2];//�������鴢����Ŀ
            int a= (int) (random.nextInt(num_range));//����
            int b= (int) (random.nextInt(num_range));//��ĸ
            int c= (int) (random.nextInt(num_range));//��һ������
            int d= (int) (random.nextInt(num_range));//��һ����ĸ
            int characters ;
            characters = (int)(random.nextInt(4));
            if(b!=0&&d!=0) {//��ĸ����Ϊ0ʱ���ɴ��з����ļ����⣬ͬʱ������
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
                    question_arry[0]=judge(a,b)+' '+'��'+' '+judge(c,d)+' '+'='+' ';
                    System.out.println(question_arry[0]);
                    results[i]=fraction_process(fenzi, fenmu);

                }
                if(characters==3&&c!=0) {
                    int fenzi=a*d;
                    int fenmu=b*c;
                    question_arry[0]=judge(a,b)+' '+'��'+' '+judge(c,d)+' '+'='+' ';
                    System.out.println(question_arry[0]);
                    results[i]=fraction_process(fenzi, fenmu);

                }
                if(characters==3&&c==0) {
                    break;
                }

            }
            else {//��ĸ����һ��Ϊ0ʱ����ֻ������ʽ������ʽ��ͬʱ������
                b=1; d=1;
                if(characters==0) {
                    int fenzi=a*d+b*c;
                    int fenmu=b*d;
                    question_arry[0]=a+" "+'+'+" "+c+" "+'='+" ";
                    System.out.println(question_arry[0]);
                    results[i]=fraction_process(fenzi, fenmu);

                }
                if(characters==1&&a*d-b*c>=0) {
                    int fenzi=a*d-b*c;
                    int fenmu=b*d;
                    question_arry[0]=a+" "+'-'+" "+c+" "+'='+" ";
                    System.out.println(question_arry[0]);
                    results[i]=fraction_process(fenzi, fenmu);

                }
                if(characters==1&&a*d-b*c<0) {
                    int fenzi=b*c-a*d;
                    int fenmu=b*d;
                    question_arry[0]=c+" "+'-'+" "+a+" "+'='+" ";
                    System.out.println(question_arry[0]);
                    results[i]=fraction_process(fenzi, fenmu);

                }
                if(characters==2) {
                    int fenzi=a*c;
                    int fenmu=b*d;
                    question_arry[0]=c+" "+'��'+" "+a+" "+'='+" ";
                    System.out.println(question_arry[0]);
                    results[i]=fraction_process(fenzi, fenmu);

                }
                if(characters==3&&c!=0) {
                    int fenzi=a*d;
                    int fenmu=b*c;
                    question_arry[0]=a+" "+'��'+" "+c+" "+'='+" ";
                    System.out.println(question_arry[0]);
                    results[i]=fraction_process(fenzi, fenmu);

                }
                if(characters==3&&c==0) {
                    break;

                }

            }

            //����Ŀд���ļ�
            FileWriter doc1 = null;
            try {

                File f=new File("exercise.txt");//��Ŀд��
                if(!f.exists()) {
    	        	f.createNewFile();
    	        }
                FileWriter filewriter = new FileWriter(f);
    	        filewriter.write("");//清空
    	        filewriter.flush();
    	        filewriter.close();
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
            }//ifѭ������

            FileWriter doc2 = null;
            try {

                File f=new File("answer.txt");//��д��
                if(!f.exists()) {
    	        	f.createNewFile();
    	        }
                FileWriter filewriter = new FileWriter(f);
    	        filewriter.write("");//清空
    	        filewriter.flush();
    	        filewriter.close();
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

        }//forѭ������
    }
}

