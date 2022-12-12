import smtplib
import os
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText

def send(content):
    mail_content = '''There was an error on your spring App
    Please check the logs for more information
    '''
    mail_content += content
    sender_address = os.environ.get('EMAIL_USER')
    sender_pass = os.environ.get('EMAIL_PASSWORD')
    receiver_address = 'germaneichemberger@gmail.com'

    message = MIMEMultipart()
    message['From'] = sender_address
    message['To'] = receiver_address
    message['Subject'] = 'Error on Spring App'

    message.attach(MIMEText(mail_content, 'plain'))

    session = smtplib.SMTP('smtp.gmail.com', 587)
    session.starttls()
    session.login(sender_address, sender_pass)
    text = message.as_string()
    session.sendmail(sender_address, receiver_address, text)
    session.quit()
