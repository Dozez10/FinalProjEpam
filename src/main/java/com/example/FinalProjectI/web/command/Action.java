package com.example.FinalProjectI.web.command;

import com.example.FinalProjectI.web.view.View;
/**
 *  Main interface for the Command/Action pattern implementation.
 *  @author Ivan Manuilenko
 */
public interface Action {
    /**
     * Execution method for all actions
     * @param view View that contains request and response to operate with
     * @throws Exception if application exception is thrown
     */
    void execute(View view)throws Exception;
}
