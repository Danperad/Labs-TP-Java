package com.vyatsu.lab;

public interface IParticipant extends IRunable, IJumpable{
    boolean isActive();
    void notActive();
}
