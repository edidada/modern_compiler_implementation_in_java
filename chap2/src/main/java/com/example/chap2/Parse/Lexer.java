package com.example.chap2.Parse;

import java.io.IOException;

interface Lexer {
    public java_cup.runtime.Symbol nextToken() throws IOException;
}
