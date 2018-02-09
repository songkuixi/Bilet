package com.krayc.service.impl;

import com.krayc.model.*;
import com.krayc.repository.EventRepository;
import com.krayc.repository.EventSeatRepository;
import com.krayc.repository.EventTypeRepository;
import com.krayc.repository.OrderEventSeatRepository;
import com.krayc.service.BookService;
import com.krayc.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventTypeRepository eventTypeRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventSeatRepository eventSeatRepository;

    @Autowired
    private OrderEventSeatRepository orderEventSeatRepository;

    @Autowired
    private BookService bookService;

    public EventTypeEntity findTypeByTid(Integer tid) {
        return eventTypeRepository.findOne(tid);
    }

    public List<EventTypeEntity> findAllEventTypes() {
        return eventTypeRepository.findAll();
    }

    public List<EventEntity> findAllEvents() {
        return eventRepository.findAll();
    }

    public List<EventEntity> findAvailableEvents() {
        return eventRepository.findByTimeAfter(new Timestamp(System.currentTimeMillis()));
    }

    public EventEntity findByEid(Integer eid) {
        return eventRepository.findOne(eid);
    }

    public void addEvent(EventEntity eventEntity) {
        eventRepository.saveAndFlush(eventEntity);

        bookService.createEventBookEntity(eventEntity);
    }

    public Integer unavailableSeatNumberByEvent(EventSeatEntity eventSeatEntity) {
        return orderEventSeatRepository.findByEventSeatByEsidAndIsValidIsNot(eventSeatEntity, 1).size();
    }

    public void addEventSeat(EventSeatEntity eventSeatEntity) {
        eventSeatRepository.saveAndFlush(eventSeatEntity);
    }

    public List<EventSeatEntity> findEventSeatOtherThanSeatsAndInEvent(Collection<SeatEntity> eventSeatEntities, EventEntity eventEntity) {
        return eventSeatRepository.findEventSeatEntitiesBySeatInAndEventIs(eventSeatEntities, eventEntity);
    }

    public List<EventSeatEntity> findEventSeatsByEid(EventEntity eventEntity) {
        return eventSeatRepository.findByEvent(eventEntity);
    }

}
