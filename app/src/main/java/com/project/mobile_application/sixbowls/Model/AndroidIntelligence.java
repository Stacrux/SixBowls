package com.project.mobile_application.sixbowls.Model;

/**
 * Created by Martino on 16/01/2015.
 */
public interface AndroidIntelligence {

    /**
     * this method check the board and choose a move to return as selected bowl. The Intelligence always
     * assume to chose the move for the active player
     * @param boardConfiguration : a string of type YBXBXBXBXBXBXZYBXBXBXBXBXBXBX
     * @return : an integer which represent the bowl id
     */
    public int chooseBowl(String boardConfiguration);

}
