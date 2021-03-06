package org.devfleet.esi.impl;

import org.devfleet.esi.Calendar;
import org.devfleet.esi.Character;
import org.devfleet.esi.Corporation;
import org.devfleet.esi.EsiService;
import org.devfleet.esi.KillMail;
import org.devfleet.esi.Mail;
import org.devfleet.esi.Mailbox;

import org.devfleet.esi.api.LiveApi;
import org.devfleet.esi.client.ApiClient;

import java.util.List;

public class EsiServiceImpl implements EsiService {

    private final CharacterAPIImpl characterImpl;
    private final CorporationAPIImpl corporationImpl;
    private final MailAPIImpl mailImpl;

    public EsiServiceImpl(final ApiClient client) {
        this(client, "tranquility");
    }

    public EsiServiceImpl(final ApiClient client, final String datasource) {
        this(datasource, client.createService(LiveApi.class));
    }

    public EsiServiceImpl(final String datasource, final LiveApi liveApi) {
        this.characterImpl = new CharacterAPIImpl(datasource, liveApi);
        this.corporationImpl = new CorporationAPIImpl(datasource, liveApi);
        this.mailImpl = new MailAPIImpl(datasource, liveApi);
    }

    @Override
    public Character getCharacter(Long charID) {
        return this.characterImpl.getCharacter(charID);
    }

    @Override
    public Calendar getCalendar(Long charID, Long afterEventID) {
        return this.characterImpl.getCalendar(charID, afterEventID);
    }

    @Override
    public boolean postCalendarEvent(Long charID, Long eventID, Calendar.Event.Response response) {
        return this.characterImpl.postCalendarEvent(charID, eventID, response);
    }

    @Override
    public Corporation getCorporation(Long corpID) {
        return this.corporationImpl.getCorporation(corpID);
    }

    @Override
    public List<Corporation.Member> getMembers(Long corpID) {
        return this.corporationImpl.getMembers(corpID);
    }

    @Override
    public boolean deleteMail(Long charID, Long mailID) {
        return this.mailImpl.deleteMail(charID, mailID);
    }

    @Override
    public List<Mail> getMails(Long charID, Long afterMailID, String... labels) {
        return this.mailImpl.getMails(charID, afterMailID, labels);
    }

    @Override
    public List<Mailbox> getMailboxes(Long charID) {
        return this.mailImpl.getMailboxes(charID);
    }

    @Override
    public Mail getMailContent(Long charID, Long mailID) {
        return this.mailImpl.getMailContent(charID, mailID);
    }

    @Override
    public Integer postMail(Long charID, Mail mail) {
        return this.mailImpl.postMail(charID, mail);
    }

    @Override
    public boolean updateMail(Long charID, Mail mail) {
        return this.mailImpl.updateMail(charID, mail);
    }

    @Override
    public boolean createMailbox(Long charID, Mailbox mailbox) {
        return (null == mailbox.getId())  ?
                this.mailImpl.createMailbox(charID, mailbox) : this.mailImpl.updateMailbox(charID, mailbox);
    }

    @Override
    public List<KillMail> getKillMails(Long charID, Integer maxCount, Long maxKillID, boolean withContent) {
        return this.mailImpl.getKillMails(charID, maxCount, maxKillID, withContent);
    }

    @Override
    public KillMail getKillMail(KillMail km) {
        return this.mailImpl.getKillMail(km);
    }
}
