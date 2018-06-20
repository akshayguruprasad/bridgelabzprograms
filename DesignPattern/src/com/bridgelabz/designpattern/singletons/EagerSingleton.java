package com.bridgelabz.designpattern.singletons;

import java.io.Serializable;

/**
 * @author Akshay
 * @version 1.0.0
 * @since 12-Jun-2018
 */

public class EagerSingleton  implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final EagerSingleton ref=new EagerSingleton();

    private EagerSingleton()  {}
    
    
    public static EagerSingleton getEagerSingleton() {
        return ref;
    }
    
    
}
