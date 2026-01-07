import { render, screen, fireEvent, waitFor } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import ContactForm from './ContactForm';
import { contactService } from '../../services/api';

// Mocka API-servicen
jest.mock('../../services/api', () => ({
  contactService: {
    sendMessage: jest.fn()
  }
}));

describe('ContactForm', () => {
  
  beforeEach(() => {
    // Återställ mocks före varje test
    jest.clearAllMocks();
  });

  // Test 1: Formuläret renderas med alla fält
  test('renderar formuläret med alla inputfält', () => {
    render(<ContactForm />);
    
    expect(screen.getByLabelText(/name/i)).toBeInTheDocument();
    expect(screen.getByLabelText(/email/i)).toBeInTheDocument();
    expect(screen.getByLabelText(/subject/i)).toBeInTheDocument();
    expect(screen.getByLabelText(/message/i)).toBeInTheDocument();
    expect(screen.getByRole('button', { name: /send/i })).toBeInTheDocument();
  });

  // Test 2: Validering - tomt namn
  test('visar felmeddelande när namn saknas', async () => {
    render(<ContactForm />);
    
    // Fyll i allt utom namn
    fireEvent.change(screen.getByLabelText(/email/i), { target: { value: 'test@example.com' } });
    fireEvent.change(screen.getByLabelText(/message/i), { target: { value: 'Ett testmeddelande' } });
    
    fireEvent.click(screen.getByRole('button', { name: /send/i }));
    
    expect(screen.getByText(/please enter your name/i)).toBeInTheDocument();
    expect(contactService.sendMessage).not.toHaveBeenCalled();
  });

  // Test 3: Validering - ogiltig email
  test('visar felmeddelande vid ogiltig email', async () => {
    render(<ContactForm />);
    
    fireEvent.change(screen.getByLabelText(/name/i), { target: { value: 'Test Testsson' } });
    fireEvent.change(screen.getByLabelText(/email/i), { target: { value: 'ingen-giltig-email' } });
    fireEvent.change(screen.getByLabelText(/message/i), { target: { value: 'Ett testmeddelande' } });
    
    fireEvent.click(screen.getByRole('button', { name: /send/i }));
    
    expect(screen.getByText(/please enter a valid email/i)).toBeInTheDocument();
    expect(contactService.sendMessage).not.toHaveBeenCalled();
  });

  // Test 4: Validering - tomt meddelande
  test('visar felmeddelande när meddelande saknas', async () => {
    render(<ContactForm />);
    
    fireEvent.change(screen.getByLabelText(/name/i), { target: { value: 'Test Testsson' } });
    fireEvent.change(screen.getByLabelText(/email/i), { target: { value: 'test@example.com' } });
    
    fireEvent.click(screen.getByRole('button', { name: /send/i }));
    
    expect(screen.getByText(/please write a message/i)).toBeInTheDocument();
    expect(contactService.sendMessage).not.toHaveBeenCalled();
  });

  // Test 5: Lyckat inskick visar bekräftelse
  test('visar bekräftelse vid lyckat inskick', async () => {
    contactService.sendMessage.mockResolvedValueOnce({ status: 202 });
    
    render(<ContactForm />);
    
    fireEvent.change(screen.getByLabelText(/name/i), { target: { value: 'Test Testsson' } });
    fireEvent.change(screen.getByLabelText(/email/i), { target: { value: 'test@example.com' } });
    fireEvent.change(screen.getByLabelText(/message/i), { target: { value: 'Ett testmeddelande' } });
    
    fireEvent.click(screen.getByRole('button', { name: /send/i }));
    
    expect(screen.getByText(/thank you for your message/i)).toBeInTheDocument();
    expect(contactService.sendMessage).toHaveBeenCalledWith({
      name: 'Test Testsson',
      email: 'test@example.com',
      subject: '',
      message: 'Ett testmeddelande'
    });
  });

  // Test 6: API-fel visar felmeddelande
  test('visar felmeddelande vid API-fel', async () => {
    contactService.sendMessage.mockRejectedValueOnce(new Error('Network error'));
    
    render(<ContactForm />);
    
    fireEvent.change(screen.getByLabelText(/name/i), { target: { value: 'Test Testsson' } });
    fireEvent.change(screen.getByLabelText(/email/i), { target: { value: 'test@example.com' } });
    fireEvent.change(screen.getByLabelText(/message/i), { target: { value: 'Ett testmeddelande' } });
    
    fireEvent.click(screen.getByRole('button', { name: /send/i }));
    
    // Vänta på att felmeddelandet visas efter reject
    await waitFor(() => {
      expect(screen.getByText(/something went wrong/i)).toBeInTheDocument();
    });
  });
});
