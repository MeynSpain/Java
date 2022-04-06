#pragma once
#include <exception>

/// <summary>
/// ���� �� ������ ������������ �������
/// </summary>
/// <typeparam name="T"></typeparam>
template <typename T>
class MyStack
{
private:
	
	int capacity = 200;
	
	T arr[200];

	int top;		//��������� �� ������� �������

public:

	MyStack()
	{
		top = -1;
	}

	/// <summary>
	/// ��������� �������� � ����
	/// </summary>
	void push(T value)
	{
		if (top == size - 1)
		{
			top = -1;			//������
		}
		top++;
		arr[top] = value;		
	}

	/// <summary>
	/// ���������� ������� �������� �����
	/// </summary>
	/// <returns></returns>
	T peek()
	{
		if (top > -1)
			return arr[top];
		else		
			throw std::invalid_argument("Stack is empty!");		
	}

	T pop()
	{
		if (top > -1)
		{
			top--;
			return arr[top + 1];
		}
		else
		{
			throw std::invalid_argument("Stack is empty!");
		}
	}

	void clear()
	{
		top = -1;
	}

	int size()
	{
		return top + 1;

	}


	/// <summary>
	/// ��������� ������ ���� ��� ���
	/// </summary>
	/// <returns>���� ������ - false, �� ������ - true</returns>
	bool empty()
	{
		if (top == -1)
		{
			return false;
		}
		else
		{
			return true;
		}
	}


};
