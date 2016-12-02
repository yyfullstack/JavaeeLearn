package com.yckjsoft.javaee.javabean;

import java.math.BigDecimal;

/**
 * Created by yong on 2016/10/28 0028.
 * CalculatorBean用于接收输入参数和计算
 */
public class CalculatorBean {
    //用户输入的第一个数
    private double firstNum;
    //用户输入的第二个数
    private double secondNum;
    //用户选择的操作运算符
    private char operator = '+';
    //运算结果
    private double result;

    /**
     * Getter for property 'firstNum'.
     *
     * @return Value for property 'firstNum'.
     */
    public double getFirstNum() {
        return firstNum;
    }

    /**
     * Setter for property 'firstNum'.
     *
     * @param firstNum Value to set for property 'firstNum'.
     */
    public void setFirstNum(double firstNum) {
        this.firstNum = firstNum;
    }

    /**
     * Getter for property 'secondNum'.
     *
     * @return Value for property 'secondNum'.
     */
    public double getSecondNum() {
        return secondNum;
    }

    /**
     * Setter for property 'secondNum'.
     *
     * @param secondNum Value to set for property 'secondNum'.
     */
    public void setSecondNum(double secondNum) {
        this.secondNum = secondNum;
    }

    /**
     * Getter for property 'operator'.
     *
     * @return Value for property 'operator'.
     */
    public char getOperator() {
        return operator;
    }

    /**
     * Setter for property 'operator'.
     *
     * @param operator Value to set for property 'operator'.
     */
    public void setOperator(char operator) {
        this.operator = operator;
    }

    /**
     * Getter for property 'result'.
     *
     * @return Value for property 'result'.
     */
    public double getResult() {
        return result;
    }

    /**
     * Setter for property 'result'.
     *
     * @param result Value to set for property 'result'.
     */
    public void setResult(double result) {
        this.result = result;
    }
    //用于计算
    public void calculate() {
        switch (operator) {
            case '+':
                result = firstNum + secondNum;
                break;
            case '-':
                result = firstNum - secondNum;
                break;
            case '*':
                result = firstNum * secondNum;
                break;
            case '/':
                if (secondNum == 0) {
                    throw new RuntimeException("被除数不能为0！！！");
                }

                result = firstNum / secondNum;
                result = new BigDecimal(result).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                break;
            default:
                throw new RuntimeException("对不起，传入的运算符非法！！！");
        }
    }
}
