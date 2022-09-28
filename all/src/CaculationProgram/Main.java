package CaculationProgram;

public class Main {
    public static void main(String[] args) {
        if (args.length != 4) {//����ַ��������Ƿ�Ϊ��
            System.out.println(" ");//ʹ��������ʾ����
            System.out.println("�����в���Ϊ0�����������������в���:");
            System.out.println(" ");
            System.out.println("����һ��������Ŀ�ʹ��ļ� ʾ����java CaculationProgram.Main -n 10 -r 10��-n 10��ʾ����10����Ŀ��-r 10��ʾ��Ŀ�е���ֵ��10֮�ڵ�������10��");
            System.out.println(" ");
            System.out.println("���ܶ����жϴ𰸶Դ������ù���һ������Ŀ����׼�𰸣� ʾ����java CaculationProgram.Main -e <answer>.txt -a <unchecked_answer>.txt��<answer>.txt��ʾ���ɵı�׼��·����<unchecked_answer>.txt��ʾ��Ҫ�жϵ��ļ�·��");
        } else {//���ó�ʼ����
            boolean n = false;
            boolean r = false;
            boolean e = false;
            boolean a = false;
            int number_question = 0;//��¼��Ŀ����
            int num_range = 0;//��¼���ֵķ�Χ
            String answer_path = "";
            String unchecked_path = "";

            int i = 0;
            for (String argument : args) {//�������ж�ȡ����
                i = i + 1;
                if (argument.equals("-n")) {//�ж���������Ŀ���Ǵ��ļ�
                    n = true;
                    e = false;
                    a = false;
                    number_question = Integer.parseInt(args[i]);
                } else if (argument.equals("-r")) {
                    r = true;
                    e = false;
                    a = false;
                    num_range = Integer.parseInt(args[i]);
                } else if (argument.equals("-e")) {
                    n = false;
                    r = false;
                    e = true;
                    answer_path = args[i];
                } else if (argument.equals("-a")) {
                    n = false;
                    r = false;
                    a = true;
                    unchecked_path = args[i];
                }
            }
            if (n & r) {
                try {
                    //������Ŀ����ӡ
                    GenerateQuestions.generate_questions(number_question, num_range);
                    System.out.println("��Ŀ�ļ���ַ��exercise.txt");
                    System.out.println("���ļ���ַ��answer.txt");
                } catch (NumberFormatException f) {
                    System.out.println("����������Ĳ���-n,-r " + "һ��ҪΪ������");
                }
            }
            else if(e&a) {
                //�ȶԴ𰸺���Ŀ�Ĺ�����
                System.out.println("�ж���ɣ��뵽Grade.txt�в鿴���");
                Compare compare = new Compare();
                compare.start(answer_path,unchecked_path);
            }
            else {
                System.out.println("��������ȷ�Ĳ�����");
                System.out.println("9");

            }
            //forѭ����

        }//if-elseѭ����

    }

}