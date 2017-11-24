package de.berstanio.bedwars;

import org.bukkit.Bukkit;

import java.lang.reflect.Method;

public class Countdowntimer {
    private int taskID;
    private int value;// TODO: 24.11.17 Name Ändern
    private String countDownMessage;
    private Method endMethod;
    private Object invokeObject;

    public Countdowntimer(int value, String countDownMessage, Method endMethod, Object invokeObject) {
        setValue(value);
        setCountDownMessage(countDownMessage);
        setEndMethod(endMethod);
        setInvokeObject(invokeObject);
        startCountdown();
    }

    // TODO: 24.11.17 Eventuell Scorboard
    private void startCountdown() {
        setValue(Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("BedWars"), () -> {
            setValue(getValue() - 1);
            if (getValue() != 0){
                try {
                    getEndMethod().invoke(getInvokeObject(), null);
                }catch (Exception e){
                        e.printStackTrace();
                }
                Bukkit.getScheduler().cancelTask(getValue());
            }else {
                if (getCountDownMessage() != null) {
                    Bukkit.broadcastMessage(getCountDownMessage().replace("***zeit***", "" + getValue()));
                }
            }
        },0,20));
    }

    public Object getInvokeObject() {
        return invokeObject;
    }

    public void setInvokeObject(Object invokeObject) {
        this.invokeObject = invokeObject;
    }

    public Method getEndMethod() {
        return endMethod;
    }

    public void setEndMethod(Method endMethod) {
        this.endMethod = endMethod;
    }

    public String getCountDownMessage() {
        return countDownMessage;
    }

    public void setCountDownMessage(String countDownMessage) {
        this.countDownMessage = countDownMessage;
    }

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
