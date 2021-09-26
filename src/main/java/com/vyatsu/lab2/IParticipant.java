package com.vyatsu.lab2;

public interface IParticipant extends IRunable, IJumpable{
    boolean isActive();
    void notActive();
}
