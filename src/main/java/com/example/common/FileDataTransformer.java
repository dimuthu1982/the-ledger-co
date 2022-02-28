package com.example.common;

import com.example.model.Command;

@FunctionalInterface
public interface FileDataTransformer
{
    Command transformToCommand(String rawData);
}
