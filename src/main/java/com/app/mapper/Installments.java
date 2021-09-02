package com.app.mapper;

public class Installments {

	private String TotalValuePlusInterestRate;

    private String PaymentSystemName;

    private String Value;

    private String NumberOfInstallments;

    private String PaymentSystemGroupName;

    private String Name;

    public String getTotalValuePlusInterestRate ()
    {
        return TotalValuePlusInterestRate;
    }

    public void setTotalValuePlusInterestRate (String TotalValuePlusInterestRate)
    {
        this.TotalValuePlusInterestRate = TotalValuePlusInterestRate;
    }

    public String getPaymentSystemName ()
    {
        return PaymentSystemName;
    }

    public void setPaymentSystemName (String PaymentSystemName)
    {
        this.PaymentSystemName = PaymentSystemName;
    }

    public String getValue ()
    {
        return Value;
    }

    public void setValue (String Value)
    {
        this.Value = Value;
    }

    public String getNumberOfInstallments ()
    {
        return NumberOfInstallments;
    }

    public void setNumberOfInstallments (String NumberOfInstallments)
    {
        this.NumberOfInstallments = NumberOfInstallments;
    }

    public String getPaymentSystemGroupName ()
    {
        return PaymentSystemGroupName;
    }

    public void setPaymentSystemGroupName (String PaymentSystemGroupName)
    {
        this.PaymentSystemGroupName = PaymentSystemGroupName;
    }

    public String getName ()
    {
        return Name;
    }

    public void setName (String Name)
    {
        this.Name = Name;
    }
}
