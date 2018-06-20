package com.bridgelabz.designpattern.proxy;

import java.io.IOException;

/**
 * @author Akshay
 * @version 1.0.0
 * @since 13-Jun-2018
 */

public interface CommandPrompt {

    void executeCommand(String name, String password) throws IOException;

}
