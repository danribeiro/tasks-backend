package br.ce.wcaquino.utils;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

import br.ce.wcaquino.taskbackend.utils.DateUtils;

public class DateUtilsTest {

    @Test
    public void deveRetornarTrueParaDatasFuturas() {
        LocalDate date = LocalDate.of(2030, 01,01);
        Assert.assertTrue(DateUtils.isEqualOrFutureDate(date));
    }

    @Test
    public void deveRetornarFalseParaDatasFuturas() {
        LocalDate date = LocalDate.of(2010, 01,01);
        Assert.assertFalse(DateUtils.isEqualOrFutureDate(date));
    }

    @Test
    public void deveRetornarTrueParaDataAtual() {
        LocalDate date = LocalDate.now();
        Assert.assertTrue(DateUtils.isEqualOrFutureDate(date));
    }
}
