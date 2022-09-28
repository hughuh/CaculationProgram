package CaculationProgram;

public class Main {
    public static void main(String[] args) {
        if (args.length != 4) {//检查字符串数组是否为空
            System.out.println(" ");//使命令行显示美观
            System.out.println("命令行参数为0或有误，请输入命令行参数:");
            System.out.println(" ");
            System.out.println("功能一：生成题目和答案文件 示例：java CaculationProgram.Main -n 10 -r 10（-n 10表示生成10个题目，-r 10表示题目中的数值在10之内但不包括10）");
            System.out.println(" ");
            System.out.println("功能二：判断答案对错（请先用功能一生成题目及标准答案） 示例：java CaculationProgram.Main -e <answer>.txt -a <unchecked_answer>.txt（<answer>.txt表示生成的标准答案路径，<unchecked_answer>.txt表示需要判断的文件路径");
        } else {//设置初始变量
            boolean n = false;
            boolean r = false;
            boolean e = false;
            boolean a = false;
            int number_question = 0;//记录题目数量
            int num_range = 0;//记录数字的范围
            String answer_path = "";
            String unchecked_path = "";

            int i = 0;
            for (String argument : args) {//从命令行读取参数
                i = i + 1;
                if (argument.equals("-n")) {//判断是生成题目还是打开文件
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
                    //生成题目并打印
                    GenerateQuestions.generate_questions(number_question, num_range);
                    System.out.println("题目文件地址：exercise.txt");
                    System.out.println("答案文件地址：answer.txt");
                } catch (NumberFormatException f) {
                    System.out.println("命令行输入的参数-n,-r " + "一定要为整数。");
                }
            }
            else if(e&a) {
                //比对答案和题目的功能类
                System.out.println("判定完成！请到Grade.txt中查看结果");
                Compare compare = new Compare();
                compare.start(answer_path,unchecked_path);
            }
            else {
                System.out.println("请输入正确的参数！");
                System.out.println("9");

            }
            //for循环的

        }//if-else循环的

    }

}