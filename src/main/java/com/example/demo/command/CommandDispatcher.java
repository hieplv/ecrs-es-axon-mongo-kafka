package com.example.demo.command;

import com.example.demo.entity.mongo.EventStore;
import com.example.demo.repository.mongo.EventStoreRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class CommandDispatcher {
    @Autowired
    private EventStoreRepository eventStoreRepository;

    // ObserverのListを保持
    private List<CommandHandler> commandHandlers = new ArrayList<>();

    // Observerを追加
    public void addObserver(CommandHandler commandHandler) {
        commandHandlers.add(commandHandler);
    }

    // Observerを削除
    public void deleteObserver(CommandHandler commandHandler) {
        commandHandlers.remove(commandHandler);
    }

    // Observerへ通知
    public void notifyObservers(Command command) {
        eventStoreRepository.save(new EventStore());

        for (CommandHandler commandHandler : commandHandlers) {
            commandHandler.handleCommand(command);
        }
    }
}
